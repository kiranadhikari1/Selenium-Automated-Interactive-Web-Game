// Event listener for the 'Start Game' button
document.getElementById('start-game').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/start', { method: 'POST' });
    fetchMessage();
});

// Event listener for 'y' button
document.getElementById('y-button').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=y', { method: 'POST' });
});

// Event listener for 'n' button
document.getElementById('n-button').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=n', { method: 'POST' });
});

// Event listener for 'Quit' button
document.getElementById('quit-button').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=Quit', { method: 'POST' });
});

// Event listener for 'Tackle' button
document.getElementById('tackle-button').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=tackle', { method: 'POST' });
});

// Event listener for 'Withdraw' button
document.getElementById('withdraw-button').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=withdraw', { method: 'POST' });
});

// Event listener for 'Enter' button
document.getElementById('enter-button').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=', { method: 'POST' });
});

document.getElementById('pos-0').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=0', { method: 'POST' });
});

document.getElementById('pos-1').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=1', { method: 'POST' });
});

document.getElementById('pos-2').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=2', { method: 'POST' });
});

document.getElementById('pos-3').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=3', { method: 'POST' });
});

document.getElementById('pos-4').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=4', { method: 'POST' });
});

document.getElementById('pos-5').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=5', { method: 'POST' });
});

document.getElementById('pos-6').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=6', { method: 'POST' });
});

document.getElementById('pos-7').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=7', { method: 'POST' });
});

document.getElementById('pos-8').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=8', { method: 'POST' });
});

document.getElementById('pos-9').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=9', { method: 'POST' });
});

document.getElementById('pos-10').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=10', { method: 'POST' });
});

document.getElementById('pos-11').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/input?input=11', { method: 'POST' });
});

// Buttons for rigging deck + starting hand | Scenario # 1
document.getElementById('rig-1').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/initFirstScenario', { method: 'POST' });
    fetchMessage();
});

// Buttons for rigging deck + starting hand | Scenario # 2
document.getElementById('rig-2').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/initSecondScenario', { method: 'POST' });
    fetchMessage();
});

// Reset the game
document.getElementById('reset-button').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/reset', { method: 'POST' });
});

async function fetchMessage() {
    try {
        const response = await fetch('http://127.0.0.1:8080/game/message');
        if (!response.ok) {
            console.error("failed fetching msg: ", response.statusText);
            return;
        }
        const message = await response.text();
        updateGameMessages(message);
        fetchMessage();

    } catch (error) {
        console.error("error: ", error);
    }
}

// Helper func for updating web message box
function updateGameMessages(message) {
    console.log("Adding message to web ui:", message);
    const totalMessages = document.getElementById('game-messages');
    const newMessage = document.createElement('p');
    newMessage.textContent = message;
    totalMessages.appendChild(newMessage);
    totalMessages.scrollTop = totalMessages.scrollHeight;
}