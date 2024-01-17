export interface ProductInterface {
  id?: string;
  name?: string;
  url?: string;
  price?: number;
  note?: string;
  status?: statusProduct;
}

export enum statusProduct {
  KICH_HOAT = "Kinh doanh",
  KHONG_KICH_HOAT = "Không kinh doanh",
}