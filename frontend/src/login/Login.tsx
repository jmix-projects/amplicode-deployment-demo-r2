import React, { useState, useCallback } from "react";
import { ChangeEvent } from "react";
import { Form } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
import { Button, Input } from "antd";
import { observer } from "mobx-react";
import "./Login.css";
import {securityStore} from "../index";

const Login = observer(() => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [performingLoginRequest, setPerformingLoginRequest] = useState(false);

  const changeLogin = useCallback(
    (e: ChangeEvent<HTMLInputElement>) => setUsername(e.target.value),
    [setUsername]
  );
  const changePassword = useCallback(
    (e: ChangeEvent<HTMLInputElement>) => setPassword(e.target.value),
    [setPassword]
  );

  const doLogin = useCallback(async () => {
    setPerformingLoginRequest(true);
    securityStore.login(username, password);
  }, [setPerformingLoginRequest, username, password]);

  return (
    <div className="login-form">
      <div className="title">scr-jmix</div>

      <Form layout="vertical" onFinish={doLogin}>
        <Form.Item>
          <Input
            id="input_login"
            placeholder='Login'
            onChange={changeLogin}
            value={username}
            prefix={<UserOutlined style={{ color: "rgba(0,0,0,.25)" }} />}
            size="large"
          />
        </Form.Item>
        <Form.Item>
          <Input
            id="input_password"
            placeholder='Password'
            onChange={changePassword}
            value={password}
            type="password"
            prefix={<LockOutlined style={{ color: "rgba(0,0,0,.25)" }} />}
            size="large"
          />
        </Form.Item>
        <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            size="large"
            block={true}
            loading={performingLoginRequest}
          >
            Login
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
});

export default Login;
