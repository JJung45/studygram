import React, { useState, useEffect } from "react";
import "../../styles/modal.css";
import "../../styles/Write.css";

const LikeModal = (props) => {
  const { open, close, header, likers } = props;

  return (
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section className="like">
          <header>
            {header}
            <button className="close" onClick={close}>
              &times;
            </button>
          </header>
          <main>
            {likers != null ? (
              likers.data?.map((data, index) => (
                <p>
                  {data.fullName} {data.userName}
                  <button className="follow-button">팔로우</button>
                </p>
              ))
            ) : (
              <p></p>
            )}
          </main>
        </section>
      ) : null}
    </div>
  );
};

export default LikeModal;
