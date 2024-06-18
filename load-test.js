async function runLoadTest(numRequests) {
    const url = 'http://195.250.20.251/public/get/allStudent'; // Replace with your server endpoint
    const startTimes = [];
    const endTimes = [];
    const requests = [];
    
    // Create an array of async functions to send requests
    for (let i = 0; i < numRequests; i++) {
        startTimes.push(Date.now());
        requests.push(fetch(url).then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            endTimes.push(Date.now());
            console.log(`Request ${i + 1}: Successful`);
        })
        .catch(error => {
            console.error(`Request ${i + 1}: Fetch error -`, error.message);
        }));
    }
    
    // Wait for all requests to complete
    await Promise.all(requests);
    
    // Calculate total duration and average response time
    const durations = endTimes.map((end, index) => end - startTimes[index]);
    const totalDuration = durations.reduce((acc, curr) => acc + curr, 0);
    const avgResponseTime = totalDuration / numRequests;
    
    console.log(`Completed ${numRequests} requests.`);
    console.log(`Average response time: ${avgResponseTime.toFixed(2)} ms`);
}

// Parse command-line arguments
const numRequests = parseInt(process.argv[2], 10); // The first command-line argument after 'node' and 'script.js'

// Validate numRequests
if (!numRequests || isNaN(numRequests) || numRequests <= 0) {
    console.error('Invalid number of requests. Please provide a positive integer.');
    process.exit(1);
}

// Run load test with the specified number of requests
runLoadTest(numRequests).catch(console.error);
