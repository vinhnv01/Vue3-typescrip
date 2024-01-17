import type { ProductInterface } from "@/types/ProductInterface";
import { requestAdmin } from "../helper/request";

export class ProductAPI {
  static fetchAll = () => {
    return requestAdmin({
      method: "GET",
      url: `/product`,
    });
  };

  static getOne = (id: string) => {
    return requestAdmin({
      method: "GET",
      url: `/product/${id}`,
    });
  };

  static create = (data: ProductInterface) => {
    return requestAdmin({
      method: "POST",
      url: `/product`,
      data: data,
    });
  };

  static update = (data: ProductInterface) => {
    return requestAdmin({
      method: "PUT",
      url: `/product/${data.id}`,
      data: data,
    });
  };

  static delete = (id: string) => {
    return requestAdmin({
      method: "DELETE",
      url: `/product/${id}`,
    });
  };
}
