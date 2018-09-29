// adopted from openzeppellin

// this thing nothing working. should an async mode?
function inLogs2(tx, eventName, eventArgs = {}) {
    const event = tx.logs.find(e => e.event === eventName);
    assert.notEmpty(event);
    for (const [k, v] of Object.entries(eventArgs)) {
        assert.isNotEmpty(event.args[k]);
        assert.equal(event.args[k], v);
    }
    return event;
}

function inLogs (logs, eventName, eventArgs = {}) {
    const event = logs.find(e => e.event === eventName);
    assert.isNotEmpty(event);
    for (const [k, v] of Object.entries(eventArgs)) {
        assert.isNotEmpty(event.args[k]);
        assert.equal(event.args[k], v);
    }
    return event;
}


async function inTx(tx, eventName, eventArgs = {}) {
    const logs = await tx;
    return inLogs(logs, eventName, eventArgs);
}

module.exports = {
    inLogs,
    inTx,
};
