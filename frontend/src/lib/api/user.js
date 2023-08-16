import client from "./client";

const prefix = "/users";

/**
 * 본인 정보를 가지고 온다.
 */
const myInfo = () =>
  client({
    method: "get",
    url: `${prefix}/info`,
  });

/**
 * 특정 유저 정보를 가지고 온다.
 */
const userInfo = (userName) =>
  client({
    method: "get",
    url: `${prefix}/${userName}/info`,
  });

/**
 * 프로필 이미지 업데이트
 */
const userProfileImageUpload = (userIdx, file) =>
    client({
        method: "put",
        url: `${prefix}/${userIdx}/profile`,
        data: file,
    });

export default { myInfo, userInfo, userProfileImageUpload };
