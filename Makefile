.PHONY: test jar

test:
	./gradlew test runGameTestServer

jar:
	./gradlew clean jar
	@ls build/libs/*.jar
