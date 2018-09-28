function nowInSeconds () {
    let now = Math.round(new Date().getTime()/1000); // turn to seconds since epoch.
    return now;
}

module.exports = {
    TimeUtil: {
        nowInSeconds: nowInSeconds
    }
};
