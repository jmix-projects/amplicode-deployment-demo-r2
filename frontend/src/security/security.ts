import {action, makeObservable, observable} from "mobx";
import axios from "axios";
import qs from 'qs';

export class SecurityStore {
  @observable isLoggedIn: boolean = true;

  constructor() {
    makeObservable(this);
  }

  @action
  async login(username: string, password: string) {
    const response = await axios('/login', {
      method: 'POST',
      headers: { 'content-type': 'application/x-www-form-urlencoded' },
      data: qs.stringify({
        username,
        password
      })
    });
    if (response.status === 200) {
      this.isLoggedIn = true;
    }
  }

  @action
  async logout() {
    this.isLoggedIn = false;
  }
}