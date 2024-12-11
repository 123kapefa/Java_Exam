import { useEffect, useState } from 'react'
import Button from '../../../ui-elements/button/Button.jsx'
import Field from '../../../ui-elements/field/Field.jsx'

import styles from './Auth.module.scss'
import Layout from '../../Layout.jsx'
import { useAuthPage } from './useAuthPage.js'


const Auth = () => {
	const {
		errors,
		handleSubmit,
		isLoading,
		onSubmitLogin,
		onSubmitRegister,
		register,
	} = useAuthPage()
	
	const [typeForm, setTypeForm] = useState('login')
	
	return (
		<>
			<Layout heading='Sign in' bgImage='/images/auth-bg.png' />
			<div className={styles.wrapperInnerPage}>
				{isLoading ?  <div>Загрузка...</div> : <div></div>}
				{typeForm === 'login' ? (
					<form onSubmit={handleSubmit(onSubmitLogin)} style={{width:'400px'}}>
						<Field
							error={errors?.email?.message}
							name='email'
							register={register}
							options={{
								required: 'Email is required'
							}}
							type='text'
							placeholder='Enter email'
						/>
	
						<Field
							error={errors?.password?.message}
							name='password'
							register={register}
							options={{
								required: 'Password is required'
							}}
							type='password'
							placeholder='Enter password'
						/>
					</form>
				)
				:
				(
					<form onSubmit={handleSubmit(onSubmitRegister)} style={{width:'400px'}}>
						<Field
							error={errors?.email?.message}
							name='login'
							register={register}
							options={{
								required: 'login is required'
							}}
							type='text'
							placeholder='Enter login'
						/>
						
						<Field
							error={errors?.email?.message}
							name='email'
							register={register}
							options={{
								required: 'Email is required'
							}}
							type='text'
							placeholder='Enter email'
						/>
	
						<Field
							error={errors?.password?.message}
							name='password'
							register={register}
							options={{
								required: 'Password is required'
							}}
							type='password'
							placeholder='Enter password'
						/>
					</form>
				)}
				
				<div className={styles.wrapperButtons}>
					<Button type="button" clickHandler={() => {
						if (typeForm === 'login') {
							handleSubmit(onSubmitLogin)();
						} else {
							setTypeForm('login')
						}
					}}>Sign in</Button>
					<Button type="button" clickHandler={() => {
						if (typeForm === 'register') {
							handleSubmit(onSubmitRegister)();
						} else {
							setTypeForm('register')
						}
					}}>Sign up</Button>
				</div>
				
			</div>
		</>
	)
}

export default Auth