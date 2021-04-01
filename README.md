# OpenAPI Vert.x Generator proposal

This is a small project showing what the current generator does and what we would like it to do...

Start by downloading the generator to create the `baseline`:

```bash
wget https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/5.1.0/openapi-generator-cli-5.1.0.jar
java -jar openapi-generator-cli-5.1.0.jar generate -i petstore.yaml -g java-vertx-web -o baseline
```

At this point you should have the baseline, the ultimate goal is to patch the templates on the generator to achieve the result on the `goal`.

## Why

The "goal" tries to address several rough edges in the current generator.

1. Use `@DataObject` for POJOs. This will allow users to use the POJOs from Java, Kotlin, RX or JavaScript, instead of forcing the Java idiomatic syntaxt by using only jackson annotations.
2. Prefer `@VertxGen` API, like the previous option, this choice will allow further `codegen` processing to generate RX, Kotlin, JavaScript bindings which would make the code more idiomatic for non Java developers.
3. Prefer composition over extension. The user will not need to extend Java interfaces to build the API. A missing implementation will not break the build. The users can now use composition, which follows the general style of APIs seen across the whole vert.x ecosystem.
4. Type validation is performed in the internal generated class, and exposed into the `RoutingContext` data. Users do not need to worry about the usages of the class `RequestParameters`.
5. The development process can now be used in an iterative way. Edit the openapi document, re-run the generator. The files `pom.xml`, `README.md` and `HttpServerVerticle.java` should not be modified. All the remaining files are free to be overwritten without loss for the user code.

## Next

Once the basics are in place we can extend the generator further with security schemas if needed.