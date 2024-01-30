import axios from "axios";
import { AppConfig } from "../AppConfig";

export const requestAdmin = axios.create({
  baseURL: AppConfig.apiUrl,
});
