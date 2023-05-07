export const humanReadable = (value, text) => {
    let hr =
        value == 0 ? `No ${text}s` : value == 1 ? `1 ${text}` : `${value} ${text}s`
    return hr
}