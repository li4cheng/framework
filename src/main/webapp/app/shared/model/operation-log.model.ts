import { Moment } from 'moment';

export interface IOperationLog {
  id?: number;
  operationLogCode?: string;
  operatorId?: number;
  operatorName?: string;
  operationTime?: Moment;
  interfaceName?: string;
}

export class OperationLog implements IOperationLog {
  constructor(
    public id?: number,
    public operationLogCode?: string,
    public operatorId?: number,
    public operatorName?: string,
    public operationTime?: Moment,
    public interfaceName?: string
  ) {}
}
