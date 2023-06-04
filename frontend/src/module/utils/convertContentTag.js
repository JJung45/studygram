
export function convertContentTag(content, postTags) {
    let postContent = content;
    // '#' 을 감지하여 a태그로 감싸기
    // postTags.map((item) => {
    //     const tagRegex = new RegExp('#' + item.content, 'g');
    //     postContent = postContent.replace(tagRegex, (match) => (
    //         <Link to={`/tagPost/${item.idx}`} key={match}>
    //             {match}
    //         </Link>
    //     ))
    // });

    postTags.forEach((item) => {
        const tagRegex = new RegExp('#' + item.content, 'g');
        postContent = postContent.replace(tagRegex,
            `<a href="/tagPost/${item.idx}">#${item.content}</a>`);
    });

    // const replacedContent = postTags.reduce((content, item) => {
    //     const tagRegex = new RegExp('#' + item.content, 'g');
    //     return content.replace(tagRegex, (match) => (
    //             <Link to={`/tagPost/${item.idx}`} key={match}>
    //                 {match}
    //             </Link>
    //         ));
    // }, postContent)

    return <span className="at-tag" dangerouslySetInnerHTML={{ __html: postContent }} />;
}
