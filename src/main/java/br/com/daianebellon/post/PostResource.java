package br.com.daianebellon.post;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface PostResource extends PanacheEntityResource<Post, Long> {

}
