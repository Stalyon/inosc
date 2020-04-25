import { Moment } from 'moment';

export interface IBom {
  id?: number;
  itemNumber?: string;
  bomOperationNumber?: number;
  bomSequenceNumber?: number;
  componentNumber?: string;
  quantity?: number;
  bomYield?: number;
  effDate?: Moment;
  disDate?: Moment;
}

export class Bom implements IBom {
  constructor(
    public id?: number,
    public itemNumber?: string,
    public bomOperationNumber?: number,
    public bomSequenceNumber?: number,
    public componentNumber?: string,
    public quantity?: number,
    public bomYield?: number,
    public effDate?: Moment,
    public disDate?: Moment
  ) {}
}
