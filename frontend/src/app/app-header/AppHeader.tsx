import {Button, Modal, notification, Space} from "antd";
import { LogoutOutlined } from "@ant-design/icons";
import { useCallback } from "react";
import { securityStore } from "../../index";
import "./AppHeader.css";
import { useIntl } from "react-intl";

export const AppHeader = () => {
  const intl = useIntl();

  const showLogoutConfirm = useCallback(() => {
    Modal.confirm({
      content: "Are you sure you want to logout?",
      okText: "OK",
      cancelText: "Cancel",
      onOk: () => {
        securityStore.logout((status) => {
          if (status !== 200) {
            notification.error({
              message: intl.formatMessage({id: "auth.logout.unknownError"})
            });
          }
        });
      }
    });
  }, [intl]);

  return (
    <>
      <div className="app-header">
        <Space className="app-header__user-panel">
          <Button
            id="button_logout"
            className="app-header__user-panel__logout-btn"
            type="text"
            icon={<LogoutOutlined />}
            onClick={showLogoutConfirm}
          />
        </Space>
      </div>
    </>
  );
};
