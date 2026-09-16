package traincraft.client.screen;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import traincraft.Traincraft;
import traincraft.network.BookRecipe;
import traincraft.network.RecipeBookPayload;
import traincraft.vehicle.item.RollingStockItem;

/** Community Edition's {@code GuiRecipeBook}: the guide, then every train workbench and assembly table recipe. */
public class RecipeBookScreen extends Screen {

    private static final Identifier COVER = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/book/bookcover.png");
    private static final Identifier LEFT = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/book/bookleft.png");
    private static final Identifier RIGHT = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/book/bookright.png");
    private static final Identifier SEARCH = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/book/searchbar.png");
    private static final Identifier WORKBENCH = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/crafting_table.png");
    private static final Identifier[] TIERS = {
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_tieri_ironage.png"),
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_tierii_steelage.png"),
            Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/gui_tieriii_advancedage.png")};
    private static final int BOOK_WIDTH = 206;
    private static final int BOOK_HEIGHT = 200;
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int HIGHLIGHT = 0xFF21D0AD;
    private static final int[][] ASSEMBLY_SLOTS = {{94, 76}, {113, 143}, {148, 143}, {214, 143}, {148, 77}, {184, 77}, {149, 110}, {185, 110}, {94, 110}, {214, 77}};
    private static final Pattern ENCLOSED = Pattern.compile("[\\[|\\(](.+)[\\)|\\]]");
    private static final Pattern JUMP = Pattern.compile("^:(\\d+)$");
    private static final List<GuidePage> GUIDE = loadGuide();

    private static int lastPage;

    private record GuidePage(String text, String side, List<List<Object>> items) {}

    private final List<BookRecipe> workbench;
    private final List<BookRecipe> assembly;
    private final int spreads = GUIDE.size() / 2;
    private final int workbenchSpreads;
    private final int totalPages;
    private final TreeMap<String, List<Integer>> index = new TreeMap<>();
    private final List<Integer> results = new ArrayList<>();
    private int page;
    private int result;
    private EditBox searchBar;
    private CustomButton back;
    private CustomButton next;
    private CustomButton previous;
    private CustomButton searchPrevious;
    private CustomButton searchNext;
    private CustomButton search;

    public RecipeBookScreen(RecipeBookPayload payload) {
        super(Component.literal("Traincraft guide"));
        workbench = payload.workbench();
        assembly = payload.assembly();
        workbenchSpreads = (workbench.size() + 1) / 2;
        totalPages = spreads + workbenchSpreads + (assembly.size() + 1) / 2;
        page = Math.min(lastPage, totalPages - 1);
        for (int i = 0; i < workbench.size(); i++) {
            addToIndex(workbench.get(i).output().getHoverName().getString().toLowerCase(), spreads + i / 2);
        }
        for (int i = 0; i < assembly.size(); i++) {
            addToIndex(assembly.get(i).output().getHoverName().getString().toLowerCase(), spreads + workbenchSpreads + i / 2);
        }
    }

    private static List<GuidePage> loadGuide() {
        try (var stream = RecipeBookScreen.class.getResourceAsStream("/assets/tc/book/guide.json")) {
            return new Gson().fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), new TypeToken<List<GuidePage>>() {}.getType());
        } catch (java.io.IOException exception) {
            throw new IllegalStateException("Cannot load the guide", exception);
        }
    }

    private void addToIndex(String name, int bookPage) {
        index.computeIfAbsent(name, key -> new ArrayList<>()).add(bookPage);
        for (String term : name.split(" ")) {
            Matcher matcher = ENCLOSED.matcher(term);
            if (matcher.find()) {
                index.computeIfAbsent(matcher.group(1), key -> new ArrayList<>()).add(bookPage);
            }
            index.computeIfAbsent(term, key -> new ArrayList<>()).add(bookPage);
        }
    }

    @Override
    protected void init() {
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        searchBar = new EditBox(font, halfWidth + 21, halfHeight - BOOK_HEIGHT / 2 - 9, 136, 10, Component.literal("Search"));
        searchBar.setBordered(false);
        searchBar.setMaxLength(25);
        searchBar.setTextColor(BLACK);
        searchBar.setCanLoseFocus(false);
        searchBar.setResponder(value -> resetSearch());
        addRenderableWidget(searchBar);
        back = addRenderableWidget(new CustomButton(LEFT, halfWidth + 150, halfHeight + 80, 23, 13, 0, 206, 23, Component.literal("Back to cover"), () -> turnTo(0)));
        next = addRenderableWidget(new CustomButton(LEFT, halfWidth + 150, halfHeight + 80, 23, 13, 0, 206, 23, Component.literal("Next page"), () -> turnTo(page + 1)));
        previous = addRenderableWidget(new CustomButton(LEFT, halfWidth - 180, halfHeight + 80, 23, 13, 0, 219, 23, Component.literal("Previous page"), () -> turnTo(page - 1)));
        searchPrevious = addRenderableWidget(new CustomButton(SEARCH, halfWidth + 160, halfHeight - BOOK_HEIGHT / 2 - 10, 10, 10, 20, 14, 20, Component.literal("Previous result"), () -> showResult(result - 1)));
        searchNext = addRenderableWidget(new CustomButton(SEARCH, halfWidth + 170, halfHeight - BOOK_HEIGHT / 2 - 10, 10, 10, 30, 14, 30, Component.literal("Next result"), () -> showResult(result + 1)));
        search = addRenderableWidget(new CustomButton(SEARCH, halfWidth + 7, halfHeight - BOOK_HEIGHT / 2 - 10, 10, 10, 40, 14, 50, Component.literal("Search"), this::runSearch));
        setInitialFocus(searchBar);
        updateButtons();
    }

    private void updateButtons() {
        searchBar.visible = page > 0;
        back.visible = page == totalPages - 1;
        next.visible = page > 0 && page < totalPages - 1;
        previous.visible = page > 0;
        searchPrevious.visible = page > 0;
        searchNext.visible = page > 0;
        search.visible = page > 0;
    }

    private void turnTo(int target) {
        page = Math.clamp(target, 0, totalPages - 1);
        updateButtons();
    }

    private void runSearch() {
        String query = searchBar.getValue().toLowerCase();
        if (results.isEmpty()) {
            var matches = query.isEmpty() ? index : index.subMap(query, query + Character.MAX_VALUE);
            for (List<Integer> pages : matches.values()) {
                for (int bookPage : pages) {
                    if (!results.contains(bookPage)) {
                        results.add(bookPage);
                    }
                }
            }
        }
        showResult(result);
    }

    private void showResult(int position) {
        if (results.isEmpty() || position < 0 || position >= results.size()) {
            return;
        }
        result = position;
        turnTo(results.get(result));
    }

    private void resetSearch() {
        result = 0;
        results.clear();
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        if (event.isEscape() && !searchBar.getValue().isEmpty()) {
            searchBar.setValue("");
            return true;
        }
        if (event.isConfirmation() && !searchBar.getValue().isEmpty()) {
            Matcher matcher = JUMP.matcher(searchBar.getValue());
            if (matcher.find()) {
                turnTo(Integer.parseInt(matcher.group(1)) - 1);
                searchBar.setValue("");
            } else {
                runSearch();
            }
            return true;
        }
        return super.keyPressed(event);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (page == 0 && event.x() >= width / 2 - 8 && event.x() < width / 2 + 32 && event.y() >= height / 2 + 98 && event.y() < height / 2 + 118) {
            turnTo(1);
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partial) {
        int left = width / 2;
        int top = height / 2 - BOOK_HEIGHT / 2;
        if (page > 0) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, RIGHT, left, top, 0, 0, BOOK_WIDTH, BOOK_HEIGHT + 20, 256, 256);
            graphics.blit(RenderPipelines.GUI_TEXTURED, SEARCH, left, top - 12, 0, 0, 192, 13, 256, 256);
            left -= BOOK_WIDTH;
            graphics.blit(RenderPipelines.GUI_TEXTURED, LEFT, left, top, 256 - BOOK_WIDTH, 0, BOOK_WIDTH, BOOK_HEIGHT, 256, 256);
        } else {
            graphics.blit(RenderPipelines.GUI_TEXTURED, COVER, left - 55, top - 15, 0, 0, 256, 256, 256, 256);
        }
        String indicator = Component.translatable("book.pageIndicator", page + 1, totalPages).getString();
        int indicatorWidth = font.width(indicator);
        if (page > 0) {
            graphics.text(font, indicator, left - indicatorWidth + BOOK_WIDTH - 44, top + 7, BLACK, false);
        }
        super.extractRenderState(graphics, mouseX, mouseY, partial);
        if (page < spreads) {
            drawGuide(graphics, GUIDE.get(page * 2), left + 36, left, top);
            drawGuide(graphics, GUIDE.get(page * 2 + 1), left + 250, left + 210, top);
        } else if (page < spreads + workbenchSpreads) {
            int first = (page - spreads) * 2;
            drawWorkbench(graphics, first, left + 194, top);
            drawWorkbench(graphics, first + 1, left, top);
        } else {
            int first = (page - spreads - workbenchSpreads) * 2;
            drawAssembly(graphics, first, left - 125, top - 33, indicatorWidth, 271);
            drawAssembly(graphics, first + 1, left - 50, top - 33, indicatorWidth, 0);
        }
    }

    private void drawGuide(GuiGraphicsExtractor graphics, GuidePage guidePage, int textX, int itemsX, int top) {
        graphics.textWithWordWrap(font, Component.literal(guidePage.text()), textX, top + 32, 140, BLACK, false);
        for (List<Object> item : guidePage.items()) {
            ItemStack stack = new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.parse((String) item.get(0))));
            graphics.item(stack, itemsX + ((Number) item.get(1)).intValue(), top + ((Number) item.get(2)).intValue());
        }
    }

    private void drawWorkbench(GuiGraphicsExtractor graphics, int recipeIndex, int left, int top) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, WORKBENCH, left + 20, top + 50, 0, 0, 177, 80, 256, 256);
        if (recipeIndex >= workbench.size()) {
            return;
        }
        BookRecipe recipe = workbench.get(recipeIndex);
        for (int slot = 0; slot < recipe.inputs().size(); slot++) {
            ItemStack stack = recipe.inputs().get(slot);
            if (!stack.isEmpty()) {
                graphics.item(stack, left + 50 + slot % 3 * 18, top + 67 + slot / 3 * 18);
            }
        }
        graphics.item(recipe.output(), left + 145, top + 85);
        String name = recipe.output().getHoverName().getString();
        graphics.text(font, name, left + 20, top + 40, matchesSearch(name) ? HIGHLIGHT : BLACK, false);
        graphics.text(font, "Crafted in: Train Workbench", left + 20, top + 130, BLACK, false);
        if (recipe.craftingTable()) {
            graphics.text(font, "Also crafted in: Crafting Table", left + 20, top + 140, BLACK, false);
        }
    }

    private void drawAssembly(GuiGraphicsExtractor graphics, int recipeIndex, int base, int top, int indicatorWidth, int offset) {
        if (recipeIndex >= assembly.size()) {
            return;
        }
        boolean right = offset > 0;
        BookRecipe recipe = assembly.get(recipeIndex);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TIERS[recipe.tier() - 1], base + (right ? 340 : 70), top + 50, 0, 0, 177, 163, 256, 256);
        for (int slot = 0; slot < ASSEMBLY_SLOTS.length; slot++) {
            ItemStack stack = recipe.inputs().get(slot);
            if (!stack.isEmpty()) {
                graphics.item(stack, base + offset + ASSEMBLY_SLOTS[slot][0], top + ASSEMBLY_SLOTS[slot][1]);
                graphics.itemDecorations(font, stack, base + offset + ASSEMBLY_SLOTS[slot][0], top + ASSEMBLY_SLOTS[slot][1]);
            }
        }
        graphics.item(recipe.output(), base + (right ? 432 : 162), top + 177);
        String name = recipe.output().getItem() instanceof RollingStockItem ? recipe.output().getHoverName().getString() : "";
        int textLeft = base - indicatorWidth + BOOK_WIDTH;
        graphics.text(font, "Tier: " + recipe.tier(), textLeft + (right ? 338 : -56), top + 40, BLACK, false);
        graphics.text(font, font.plainSubstrByWidth(name, 150), textLeft + (right ? 225 : -45), top + 56, matchesSearch(name) ? HIGHLIGHT : WHITE, false);
    }

    private boolean matchesSearch(String name) {
        return !searchBar.getValue().isEmpty() && name.toLowerCase().contains(searchBar.getValue().toLowerCase());
    }

    @Override
    public void removed() {
        lastPage = page;
        super.removed();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
