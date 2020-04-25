export interface IStock {
  id?: number;
  itemNumber?: string;
  magasin?: string;
  emplacement?: string;
  qteStk?: number;
}

export class Stock implements IStock {
  constructor(
    public id?: number,
    public itemNumber?: string,
    public magasin?: string,
    public emplacement?: string,
    public qteStk?: number
  ) {}
}
