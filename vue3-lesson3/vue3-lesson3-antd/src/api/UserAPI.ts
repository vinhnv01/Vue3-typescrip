import { UserInterface } from "./../types/UserInterface";
import { requestAdmin } from "../helper/request";

export class UserAPI {
  static fetchAll = () => {
    return requestAdmin({
      method: "GET",
      url: `/user`,
    });
  };

  static getOne = (id: string) => {
    return requestAdmin({
      method: "GET",
      url: `/user/${id}`,
    });
  };

  static create = (data: any) => {
    return requestAdmin({
      method: "POST",
      url: `/user`,
      data: data,
    });
  };

  static update = (data: UserInterface) => {
    return requestAdmin({
      method: "PUT",
      url: `/user/${data.id}`,
      data: data,
    });
  };

  static delete = (id: string) => {
    return requestAdmin({
      method: "DELETE",
      url: `/user/${id}`,
    });
  };
}
