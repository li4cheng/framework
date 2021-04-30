package com.my.framework.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.my.framework.domain.enumeration.MenuStatusType;

/**
 * 菜单表
 */
@ApiModel(description = "菜单表")
@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建人
     */
    @Size(max = 64)
    @ApiModelProperty(value = "创建人")
    @Column(name = "created_by", length = 64)
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "created_date")
    private Instant createdDate;

    /**
     * 更新人
     */
    @Size(max = 64)
    @ApiModelProperty(value = "更新人")
    @Column(name = "last_modified_by", length = 64)
    private String lastModifiedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    @Column(name = "deleted")
    private Boolean deleted;

    /**
     * 菜单编码
     */
    @Size(max = 64)
    @ApiModelProperty(value = "菜单编码")
    @Column(name = "code", length = 64)
    private String code;

    /**
     * 所有父级ID
     */
    @Size(max = 3072)
    @ApiModelProperty(value = "所有父级ID")
    @Column(name = "parent_ids", length = 3072)
    private String parentIds;

    /**
     * 同级排序
     */
    @ApiModelProperty(value = "同级排序")
    @Column(name = "sibling_sort")
    private Integer siblingSort;

    /**
     * 全局排序
     */
    @ApiModelProperty(value = "全局排序")
    @Column(name = "global_sort")
    private Integer globalSort;

    /**
     * 是否最末级
     */
    @ApiModelProperty(value = "是否最末级")
    @Column(name = "leaf")
    private Boolean leaf;

    /**
     * 层次级别
     */
    @ApiModelProperty(value = "层次级别")
    @Column(name = "level")
    private Integer level;

    /**
     * 菜单简称
     */
    @Size(max = 128)
    @ApiModelProperty(value = "菜单简称")
    @Column(name = "short_name", length = 128)
    private String shortName;

    /**
     * 菜单全名
     */
    @Size(max = 128)
    @ApiModelProperty(value = "菜单全名")
    @Column(name = "full_name", length = 128)
    private String fullName;

    /**
     * 链接
     */
    @Size(max = 2000)
    @ApiModelProperty(value = "链接")
    @Column(name = "href", length = 2000)
    private String href;

    /**
     * 图标
     */
    @Size(max = 2000)
    @ApiModelProperty(value = "图标")
    @Column(name = "icon", length = 2000)
    private String icon;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示")
    @Column(name = "visible")
    private Boolean visible;

    /**
     * 菜单状态
     */
    @ApiModelProperty(value = "菜单状态")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MenuStatusType status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SysMenu createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SysMenu createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public SysMenu lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public SysMenu lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public SysMenu deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCode() {
        return code;
    }

    public SysMenu code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentIds() {
        return parentIds;
    }

    public SysMenu parentIds(String parentIds) {
        this.parentIds = parentIds;
        return this;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getSiblingSort() {
        return siblingSort;
    }

    public SysMenu siblingSort(Integer siblingSort) {
        this.siblingSort = siblingSort;
        return this;
    }

    public void setSiblingSort(Integer siblingSort) {
        this.siblingSort = siblingSort;
    }

    public Integer getGlobalSort() {
        return globalSort;
    }

    public SysMenu globalSort(Integer globalSort) {
        this.globalSort = globalSort;
        return this;
    }

    public void setGlobalSort(Integer globalSort) {
        this.globalSort = globalSort;
    }

    public Boolean isLeaf() {
        return leaf;
    }

    public SysMenu leaf(Boolean leaf) {
        this.leaf = leaf;
        return this;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Integer getLevel() {
        return level;
    }

    public SysMenu level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getShortName() {
        return shortName;
    }

    public SysMenu shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public SysMenu fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHref() {
        return href;
    }

    public SysMenu href(String href) {
        this.href = href;
        return this;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public SysMenu icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean isVisible() {
        return visible;
    }

    public SysMenu visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public MenuStatusType getStatus() {
        return status;
    }

    public SysMenu status(MenuStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(MenuStatusType status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysMenu)) {
            return false;
        }
        return id != null && id.equals(((SysMenu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysMenu{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", code='" + getCode() + "'" +
            ", parentIds='" + getParentIds() + "'" +
            ", siblingSort=" + getSiblingSort() +
            ", globalSort=" + getGlobalSort() +
            ", leaf='" + isLeaf() + "'" +
            ", level=" + getLevel() +
            ", shortName='" + getShortName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", href='" + getHref() + "'" +
            ", icon='" + getIcon() + "'" +
            ", visible='" + isVisible() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
