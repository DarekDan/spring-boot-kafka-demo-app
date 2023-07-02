import http from 'k6/http';
import {check} from 'k6';

export default function () {
    // Create an instance of the Mail class with sample data
    const mail = {
        id: 1,
        item: 'Sample Item',
        address: 'Sample Address',
        sendBy: 'Sample Sender',
        sendDate: '2023-07-01',
        email: 'sample@example.com'
    };

    // Serialize the Mail object to JSON
    const jsonData = JSON.stringify(mail);

    // Set the headers for the HTTP request
    const headers = {
        'Content-Type': 'application/json'
    };

    // Make a POST request to the server with the serialized JSON data
    const response = http.post('http://localhost:9094/api/v1/mailing', jsonData,
        {headers});

    // Check if the request was successful
    check(response, {
        'Status is 200': (r) => r.status === 200
    });

    // Deserialize the JSON response back to a Mail object
    // console.log(response.body);
    const responseBody = JSON.parse(response.body);
    const deserializedResult = {
        value: responseBody.value,
        success: responseBody.success
    };

    // Perform assertions to validate the deserialized Mail object
    check(deserializedResult, {
        'Success': (m) => m.success
    });
}
