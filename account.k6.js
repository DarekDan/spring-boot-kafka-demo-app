import http from 'k6/http';
import { check, sleep } from 'k6';

export default function () {
    const createAccountUrl = 'http://localhost:9094/api/v1/createAccount';
    const getAccountUrl = 'http://localhost:9094/api/v1/getAccount';

    const createAccountPayload = {
        name: 'Bob',
    };

    // Create Account
    const createAccountResponse = http.post(createAccountUrl, JSON.stringify(createAccountPayload), {
        headers: {
            'Content-Type': 'application/json',
        },
    });

    check(createAccountResponse, {
        'Create Account - Status is 200': (r) => r.status === 200,
    });

    const account = JSON.parse(createAccountResponse.body);
    const accountId = account.id;

    // Get Account
    const getAccountPayload = {
        id: accountId,
    };

    const getAccountResponse = http.post(getAccountUrl, JSON.stringify(getAccountPayload), {
        headers: {
            'Content-Type': 'application/json',
        },
    });

    check(getAccountResponse, {
        'Get Account - Status is 200': (r) => r.status === 200,
        'Get Account - Name is correct': (r) => {
            const retrievedAccount = JSON.parse(r.body);
            return retrievedAccount.name === createAccountPayload.name;
        },
        'Get Account - ID is correct': (r) => {
            const retrievedAccount = JSON.parse(r.body);
            return retrievedAccount.id === accountId;
        },
    });

    sleep(1); // optional sleep between iterations
}
