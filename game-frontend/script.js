// Method that gets logs and dispalys on web
async function fetchLogs() {
    const response = await fetch('http://127.0.0.1:8080/game/messageLogs');
    const logs = await response.json();
    logs.forEach(log => updateGameMessages(log));
    setTimeout(fetchLogs, 1000); // updates every second
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

// Event listener for the Start Game button
document.getElementById('start-game').addEventListener('click', async () => {
    await fetch('http://127.0.0.1:8080/game/start', { method: 'POST' });
    fetchLogs();
});
