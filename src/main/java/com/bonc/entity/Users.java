package com.bonc.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
//import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.Date;

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

    @Field
    private long _version_;

    public Users() {
    }

    public Users(Integer id, String name, Date create_time, long _version_) {
        this.id = id;
        this.name = name;
        this.create_time = create_time;
        this._version_ = _version_;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", create_time='" + create_time + '\'' +
                ", _version_=" + _version_ +
                '}';
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

    public long get_version_() {
        return _version_;
    }

    public void set_version_(long _version_) {
        this._version_ = _version_;
    }
}
