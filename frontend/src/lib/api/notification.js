import client from "./client";

const prefix = "/api/notification";

/**
 * 알림 목록을 조회한다.
 */
const getNotifications = () =>
  client({
    method: "get",
    url: `${prefix}/`,
  });

/**
 * 알림 개수를 조회한다.
 */
const getNotReadNotificationsCount = () =>
  client({
    method: "get",
    url: `${prefix}/counts`,
  });

export default {
  getNotReadNotificationsCount,
  getNotifications,
};
