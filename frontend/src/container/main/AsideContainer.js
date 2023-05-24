import React, {useEffect, useState} from "react";
import AsideComponent from "../../component/main/AsideComponent";

const AsideContainer = (props) => {
  const [userIdx, setUserIdx] = useState(props.userIdx);

  return <AsideComponent userIdx={userIdx}/>;
};

export default AsideContainer;
