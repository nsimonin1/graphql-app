/**
 *
 */
package org.simon.pascal.provider;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.simon.pascal.data.GraphQLDataFetchers;
import org.simon.pascal.service.AuteurService;
import org.simon.pascal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
/**
 * @author nsimonin1
 *
 */
@Component
public class GraphQLProvider {
	private GraphQL graphQL;
	@Autowired
	private GraphQLDataFetchers graphQLDataFetchers;
	@Autowired
	private AuteurService auteurService;
	@Autowired
	private BookService bookService;

	@Bean
	public GraphQL graphQL() {
		return graphQL;
	}

	@PostConstruct
	public void init() throws IOException {
		final URL url = Resources.getResource("schema.graphqls");
		final String sdl = Resources.toString(url, Charsets.UTF_8);
		final GraphQLSchema graphQLSchema = buildSchema(sdl);
		graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	private GraphQLSchema buildSchema(String sdl) {
		final TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        final RuntimeWiring runtimeWiring = buildWiring();
        final SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
	}

	private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", bookService.getBookByIdDataFetcher())
                        .dataFetcher("authorById", auteurService.getAuthorByIdDataFetcher()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", bookService.getAuthorDataFetcher()))
                .build();
    }
}
