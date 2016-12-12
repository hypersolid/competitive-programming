find . -name '*.go' | xargs gofmt -w
find . -name '*.java' | xargs java -jar ./google-java-format.jar -i
find . -name '*.py' | xargs autopep8 --in-place
find . -name '.DS_Store' | xargs rm
