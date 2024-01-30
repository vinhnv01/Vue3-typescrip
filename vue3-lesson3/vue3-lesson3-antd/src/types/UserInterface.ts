export interface UserInterface {
  id?: string;
  avatar?: string;
  name?: string;
  email?: string;
  password?: string;
  dateOfBirth?: number | string;
  gender?: GenDerUser;
  address?: string;
  status?: StatusUser;
}

export enum GenDerUser {
  "NAM",
  "NU",
}
export enum StatusUser {
  "DANG_HOAT_DONG",
  "KHONG_HOAT_DONG",
}
