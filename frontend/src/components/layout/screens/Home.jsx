import { EmailEditor } from '../components/email-editor/EmailEditor.jsx'
import { EmailList } from '../components/email-list/EmailList.jsx'
import {TabBar} from "../components/tab-bar/TabBar.jsx";
import styles from "../main.module.css";
import React from "react";
import { createBrowserRouter, RouterProvider, Outlet } from 'react-router-dom';

const router = createBrowserRouter([
    {
        path: '/',
        element: <NavbarWrapper />,
        children: [
            {
                path: 'editor',
                element: <EmailEditor />,
            },
            {
                path: 'list',
                element: <EmailList />,
            },
            {
                index: true,
                element: <EmailEditor />,
            },
        ],
    },
]);


export function Home() {
    return <RouterProvider router={router} />;
}

export function NavbarWrapper() {
    return (
        <div className={styles.container}>
            <TabBar />
            <div className={styles.content}>
                <Outlet />
            </div>
        </div>
    );
}