import React, {useEffect, useRef, useState } from "react";
import styles from "../../styles/Sidebar.css";
import SearchListComponent from "./SearchListComponent";



const SidebarComponent = ({ width=280, children }) => {
    const [isOpen, setOpen] = useState(false);
    // left
    const [xPosition, setX] = useState(width);
    const side = useRef();

    const meta = document.createElement('meta');
    meta.name = "viewport";
    meta.content = "width=device-width, initial-scale=1.0";
    document.getElementsByTagName('head')[0].appendChild(meta);

    // button 클릭 시 토글
    const toggleMenu = () => {
        if (xPosition > 0) {
            setX(0);
            setOpen(true);
        } else {
            setX(width);
            setOpen(false);
        }
    };

    // 사이드바 외부 클릭시 닫히는 함수
    const handleClose = async e => {
        let sideArea = side.current;
        let sideChildren = side.current.contains(e.target);
        if (isOpen && (!sideArea || !sideChildren)) {
            await setX(width);
            await setOpen(false);
        }
    }

    useEffect(()=> {
        window.addEventListener('click', handleClose);
        return () => {
            window.removeEventListener('click', handleClose);
        };
    })


    return (
        <div className="container">
            <div ref={side}  className="sidebar" style={{ width: `${width}px`, height: '100%',  transform: `translatex(${-xPosition +20}px)`}}>
                <button onClick={() => toggleMenu()}
                        className="button" >
                    {isOpen ?
                        <span>X</span> : <img src="images/avatar.png" alr="contact open button" className="openBtn"/>
                    }
                </button>
                <SearchListComponent/>
            </div>
        </div>
    );
};


export default SidebarComponent;