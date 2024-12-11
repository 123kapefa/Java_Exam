import { useEffect, useRef, useState } from 'react'


export const useOnClickOutside = (isInitialValue) => {
	const [isOpen, setIsOpen] = useState(isInitialValue)
	const ref = useRef(null)
	
	const handleClickOutside = event => {
		if (ref.current && !ref.current.contains(event.target)) {
			setIsOpen(false)
		}
	}
	
	useEffect(() => {
		document.addEventListener('click', handleClickOutside, true)
		return () => {
			document.removeEventListener('click', handleClickOutside. true)
		}
	})
	
	return {ref, isOpen, setIsOpen}
}