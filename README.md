# OpenAPI Vert.x Generator proposal

This is a small project showing what the current generator does and what we would like it to do...

Start by downloading the generator to create the `baseline`:

```bash
wget https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/5.1.0/openapi-generator-cli-5.1.0.jar
java -jar openapi-generator-cli-5.1.0.jar generate -i petstore.yaml -g java-vertx-web -o baseline
```

At this point you should have the baseline, the ultimate goal is to patch the templates on the generator to achieve the result on the `goal`.