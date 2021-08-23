import Cookies from 'js-cookie';
import {observable} from "mobx";
import axios from "axios";
import qs from 'qs';

export class SecurityStore {
  @observable isLoggedIn: boolean = false;

  async login(username: string, password: string) {
    const response = await axios('/login', {
      method: 'POST',
      headers: { 'content-type': 'application/x-www-form-urlencoded' },
      data: qs.stringify({
        username,
        password
      })
    });
    console.log(response);
    if (response.headers['set-cookie'].indexOf('JSESSIONID') > -1) {
      this.isLoggedIn = true;
    }
  }

  async logout() {
    Cookies.remove('JSESSIONID');
    this.isLoggedIn = false;
  }
}