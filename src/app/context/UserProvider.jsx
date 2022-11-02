import { UserContext } from './UserContext'

const userFake = {
    id: 100,
    name: 'Nahum Martinez',
    email: 'nmartinez.salgado@yahoo.com'
}

export const UserProvider = ({ children }) => {
    return (
        <UserContext.Provider value={{ user: userFake }}>
            {children}
        </UserContext.Provider>
    )
}
