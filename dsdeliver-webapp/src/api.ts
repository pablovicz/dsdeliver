import axios from "axios";
import { OrderPayload } from "./types";

const BASE_URL = process.env.REACT_APP_BASE_URL;
const mapboxToken = process.env.REACT_APP_ACCESS_TOKEN_MAP_BOX;

export function fetchProducts(){
  return axios.get(`${BASE_URL}/products`);
}


export function fetchLocalMapBox(local:string){
  return axios(`https://api.mapbox.com/geocoding/v5/mapbox.places/${local}.json?access_token=${mapboxToken}`);
}

export function saveOrder(payload: OrderPayload){

  return axios.post(`${BASE_URL}/orders`, payload);
}