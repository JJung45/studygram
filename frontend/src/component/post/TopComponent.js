const TopComponent = () => {


    return (
        <div id="header" className="header">
            <div className="arrow">
                <img src={process.env.PUBLIC_URL + '/savePost/arrowback.svg'} />
            </div>
            <div className="center">
                <h4>새 게시물</h4>
            </div>
            <div className="check">
                <img src={process.env.PUBLIC_URL + '/savePost/check.svg'} />
            </div>
        </div>
    )

};

export default TopComponent;