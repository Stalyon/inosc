import { Moment } from 'moment';

export interface IMvtStk {
  id?: number;
  dateMvt?: Moment;
  codeMvt?: string;
  itemNumber?: string;
  magasin?: string;
  emplacement?: string;
  qte?: number;
  numOrdre?: string;
  numLigneOrdre?: string;
  lotNumber?: string;
}

export class MvtStk implements IMvtStk {
  constructor(
    public id?: number,
    public dateMvt?: Moment,
    public codeMvt?: string,
    public itemNumber?: string,
    public magasin?: string,
    public emplacement?: string,
    public qte?: number,
    public numOrdre?: string,
    public numLigneOrdre?: string,
    public lotNumber?: string
  ) {}
}
