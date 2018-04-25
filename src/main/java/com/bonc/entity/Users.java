package com.bonc.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

//import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @Description: Created by mace on 14:07 2018/4/12.
 */

//@SolrDocument(solrCoreName = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 7848161309660591618L;

    @Id
    @Field
    private Integer id;

    @Field
    private String name;

    @Field
    private Date create_time;

    public Users() {
        super();
    }

    public Users(Integer id, String name, Date create_time) {
        this.id = id;
        this.name = name;
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Users{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", create_time=").append(create_time);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
