import { Moment } from 'moment';
import { MenuStatusType } from 'app/shared/model/enumerations/menu-status-type.model';

export interface ISysMenu {
  id?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  deleted?: boolean;
  code?: string;
  parentIds?: string;
  siblingSort?: number;
  globalSort?: number;
  leaf?: boolean;
  level?: number;
  shortName?: string;
  fullName?: string;
  href?: string;
  icon?: string;
  visible?: boolean;
  status?: MenuStatusType;
}

export class SysMenu implements ISysMenu {
  constructor(
    public id?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public deleted?: boolean,
    public code?: string,
    public parentIds?: string,
    public siblingSort?: number,
    public globalSort?: number,
    public leaf?: boolean,
    public level?: number,
    public shortName?: string,
    public fullName?: string,
    public href?: string,
    public icon?: string,
    public visible?: boolean,
    public status?: MenuStatusType
  ) {
    this.deleted = this.deleted || false;
    this.leaf = this.leaf || false;
    this.visible = this.visible || false;
  }
}
