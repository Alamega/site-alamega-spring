import {Metadata} from "next";
import React from "react";
import {getSession} from "@/lib";

export const metadata: Metadata = {
    title: "Главная",
    description: "Главная",
};

export default async function Home() {
    const session: Session | null = await getSession();
    return (
        <>
            {session && <p>Приветствую товарищ {session.user.username}!</p>}
            {!session && <p>Чувствую присутствие неавторизованного пользователя.</p>}
        </>
    );
}

