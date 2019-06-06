package org.simon.pascal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

@SpringBootApplication
public class GraphqlAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlAppApplication.class, args);
	}

	@Bean
    GraphQLSchema schema() {
        return GraphQLSchema.newSchema()
            .query(GraphQLObjectType.newObject()
                .name("query")
                .field(field -> field
                    .name("test")
                    .type(Scalars.GraphQLString)
                    .dataFetcher(environment -> "response")
                )
                .build())
            .build();
    }

}
