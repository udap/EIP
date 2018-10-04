// adopted from openzeppellin

// this thing nothing working. should an async mode?
function inLogs2(tx, eventName, eventArgs = {}) {
    const event = tx.logs.find(e => e.event === eventName);
    assert.isDefined(event, "event was not found");
    for (const [k, v] of Object.entries(eventArgs)) {
        assert.isNotEmpty(event.args[k]);
        assert.equal(event.args[k], v);
    }
    return event;
}

function inLogs (logs, eventName, eventArgs = {}) {
    const event = logs.find(e => e.event === eventName);
    assert.isDefined(event, "event was not found");
    for (const [k, v] of Object.entries(eventArgs)) {
        assert.isDefined(event.args[k], event.args[k] + " was not defined in the event");
        assert.equal(event.args[k], v, "argument not matched");
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
