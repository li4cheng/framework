package com.my.framework.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 地址表
 */
@ApiModel(description = "地址表")
@Entity
@Table(name = "district")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    @Column(name = "pid")
    private Long pid;

    /**
     * 深度
     */
    @ApiModelProperty(value = "深度")
    @Column(name = "deep")
    private Long deep;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Column(name = "name")
    private String name;

    /**
     * 拼音首字母
     */
    @ApiModelProperty(value = "拼音首字母")
    @Column(name = "prefix")
    private String prefix;

    /**
     * 拼音
     */
    @ApiModelProperty(value = "拼音")
    @Column(name = "pinyin")
    private String pinyin;

    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    @Column(name = "ext_name")
    private String extName;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public District pid(Long pid) {
        this.pid = pid;
        return this;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getDeep() {
        return deep;
    }

    public District deep(Long deep) {
        this.deep = deep;
        return this;
    }

    public void setDeep(Long deep) {
        this.deep = deep;
    }

    public String getName() {
        return name;
    }

    public District name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public District prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPinyin() {
        return pinyin;
    }

    public District pinyin(String pinyin) {
        this.pinyin = pinyin;
        return this;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getExtName() {
        return extName;
    }

    public District extName(String extName) {
        this.extName = extName;
        return this;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof District)) {
            return false;
        }
        return id != null && id.equals(((District) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "District{" +
            "id=" + getId() +
            ", pid=" + getPid() +
            ", deep=" + getDeep() +
            ", name='" + getName() + "'" +
            ", prefix='" + getPrefix() + "'" +
            ", pinyin='" + getPinyin() + "'" +
            ", extName='" + getExtName() + "'" +
            "}";
    }
}
