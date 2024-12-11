import React from 'react';
import {NavLink } from 'react-router-dom';
import styles from './TabBar.module.css';

export function TabBar() {
	return (
		<nav className={styles.tabBar}>
			<NavLink
				to="/editor"
				className={({isActive}) => (isActive ? styles.activeLink : styles.link)}
			>
				<p>Email Editor</p>
			</NavLink>
			<NavLink
				to="/list"
				className={({isActive}) => (isActive ? styles.activeLink : styles.link)}
			>
				<p style={{

				}}>Email List</p>
			</NavLink>
		</nav>
	);
}