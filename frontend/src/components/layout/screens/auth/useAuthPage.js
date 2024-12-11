import { useMutation } from '@tanstack/react-query'
import { useEffect, useMemo, useState } from 'react'
import { useForm } from 'react-hook-form'
import { useNavigate } from 'react-router-dom'

import { useAuth } from '../../../../hooks/useAuth.js'

import AuthService from '../../../../services/auth.service.js'

export const useAuthPage = () => {
	
	const [type, setType] = useState('login')

	const {
		register,
		handleSubmit,
		formState: { errors },
		reset
	} = useForm({
		mode: 'onSubmit'
	})

	const { isAuth, setIsAuth } = useAuth()
	const navigate = useNavigate()

	useEffect(() => {
		if (isAuth) {
			navigate('/')
		}
	}, [isAuth])
	
	const { mutate, isLoading } = useMutation({
		mutationKey: ['auth'],
		mutationFn: ({ email, password, login }) => AuthService.main(type, email, password, login),
		onSuccess: () => {
				setIsAuth(true)
				reset()
		}
	})

	const onSubmitLogin = data => {
		setType('login')
		mutate(data)
	}

	const onSubmitRegister = data => {
		setType('register')
		mutate(data)
	}
	
	return useMemo(
		() => ({
			setType,
			register,
			handleSubmit,
			errors,
			isLoading,
			onSubmitLogin,
			onSubmitRegister,
			type
		}),
		[errors, isLoading]
	)
}
