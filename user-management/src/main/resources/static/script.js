const API_BASE_URL = 'http://localhost:9000/api';

// Navegación entre secciones
function showSection(sectionId) {
    // Ocultar todas las secciones
    document.querySelectorAll('.section').forEach(section => {
        section.classList.remove('active');
    });
    
    // Remover active de todos los items del nav
    document.querySelectorAll('.nav-item').forEach(item => {
        item.classList.remove('active');
    });
    
    // Mostrar sección seleccionada
    document.getElementById(sectionId).classList.add('active');
    
    // Activar item del nav correspondiente
    document.querySelector(`[href="#${sectionId}"]`).classList.add('active');
    
    // Si es la sección de listar, cargar usuarios automáticamente
    if (sectionId === 'listar') {
        getAllUsers();
    }
}

// Verificar estado del servidor
async function checkServerStatus() {
    const statusElement = document.getElementById('serverStatus');
    
    try {
        const response = await fetch(`${API_BASE_URL}/health`);
        if (response.ok) {
            statusElement.textContent = 'Conectado';
            statusElement.className = 'status-value connected';
        } else {
            throw new Error('Servidor no responde correctamente');
        }
    } catch (error) {
        statusElement.textContent = 'Desconectado';
        statusElement.className = 'status-value disconnected';
    }
}

// Función para mostrar resultados
function showResult(elementId, content, type = 'success') {
    const element = document.getElementById(elementId);
    element.innerHTML = content;
    element.className = `result-card ${type}`;
    element.style.display = 'block';
}

// 1. Crear usuario
document.getElementById('createUserForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const user = {
        nombre: document.getElementById('nombre').value,
        apellido: document.getElementById('apellido').value,
        email: document.getElementById('email').value
    };

    try {
        const response = await fetch(`${API_BASE_URL}/users`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        });

        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }

        const result = await response.json();
        
        const content = `
            <h4>✅ Usuario creado exitosamente</h4>
            <div class="user-card">
                <h4>${result.nombre} ${result.apellido}</h4>
                <p><strong>ID asignado:</strong> ${result.id}</p>
                <p><strong>Nota:</strong> Nombre y apellido en mayúsculas</p>
            </div>
            <div class="json-view">${JSON.stringify(result, null, 2)}</div>
        `;
        
        showResult('createResult', content, 'success');
        document.getElementById('createUserForm').reset();
        
    } catch (error) {
        const content = `
            <h4>❌ Error al crear usuario</h4>
            <p>${error.message}</p>
            <p>Verifique que el servidor esté ejecutándose en puerto 9000</p>
        `;
        showResult('createResult', content, 'error');
    }
});

// 2. Obtener todos los usuarios
async function getAllUsers() {
    try {
        const response = await fetch(`${API_BASE_URL}/users`);
        
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }

        const users = await response.json();
        
        let content = '';
        
        if (users.length === 0) {
            content = '<p>No hay usuarios registrados</p>';
        } else {
            users.forEach(user => {
                content += `
                    <div class="user-card">
                        <h4>${user.nombre} ${user.apellido}</h4>
                        <p><strong>ID:</strong> ${user.id}</p>
                        <p><strong>Email:</strong> ${user.email}</p>
                    </div>
                `;
            });
        }
        
        document.getElementById('allUsersResult').innerHTML = content;
        
    } catch (error) {
        document.getElementById('allUsersResult').innerHTML = `
            <div class="result-card error">
                <h4>❌ Error al obtener usuarios</h4>
                <p>${error.message}</p>
            </div>
        `;
    }
}

// 3. Obtener usuario por ID
async function getUserById() {
    const userId = document.getElementById('userId').value;
    
    if (!userId) {
        showResult('userByIdResult', '<p>⚠️ Por favor ingresa un ID</p>', 'error');
        return;
    }

    try {
        const response = await fetch(`${API_BASE_URL}/users/${userId}`);
        
        if (response.status === 404) {
            showResult('userByIdResult', `<p>❌ Usuario con ID ${userId} no encontrado</p>`, 'error');
            return;
        }

        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }

        const user = await response.json();
        
        const content = `
            <h4>✅ Usuario encontrado</h4>
            <div class="user-card">
                <h4>${user.nombre} ${user.apellido}</h4>
                <p><strong>ID:</strong> ${user.id}</p>
                <p><strong>Email:</strong> ${user.email}</p>
            </div>
            <div class="json-view">${JSON.stringify(user, null, 2)}</div>
        `;
        
        showResult('userByIdResult', content, 'success');
        
    } catch (error) {
        const content = `
            <h4>❌ Error al buscar usuario</h4>
            <p>${error.message}</p>
        `;
        showResult('userByIdResult', content, 'error');
    }
}

// 4. Obtener parámetros en URL
async function getParams() {
    const nombre = document.getElementById('paramNombre').value;
    const apellido = document.getElementById('paramApellido').value;
    
    if (!nombre || !apellido) {
        showResult('paramsResult', '<p>⚠️ Por favor ingresa nombre y apellido</p>', 'error');
        return;
    }

    try {
        const response = await fetch(`${API_BASE_URL}/params?nombre=${encodeURIComponent(nombre)}&apellido=${encodeURIComponent(apellido)}`);
        
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }

        const result = await response.json();
        
        const content = `
            <h4>✅ Parámetros procesados</h4>
            <div class="user-card">
                <p><strong>Nombre completo:</strong> ${result.nombreCompleto}</p>
            </div>
            <div class="json-view">${JSON.stringify(result, null, 2)}</div>
        `;
        
        showResult('paramsResult', content, 'success');
        
    } catch (error) {
        const content = `
            <h4>❌ Error al procesar parámetros</h4>
            <p>${error.message}</p>
        `;
        showResult('paramsResult', content, 'error');
    }
}

// Inicialización
document.addEventListener('DOMContentLoaded', function() {
    checkServerStatus();
    // Cargar usuarios automáticamente al inicio si está en la sección de listar
    if (document.getElementById('listar').classList.contains('active')) {
        getAllUsers();
    }
});