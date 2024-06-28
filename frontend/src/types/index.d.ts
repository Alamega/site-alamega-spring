declare interface User {
    token: string
    id: string;
    username: string;
    role: string;
}

declare interface Post {
    id: string;
    text: string;
    date: string;
}

declare interface Session {
    user: User;
}