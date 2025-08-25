document.addEventListener('DOMContentLoaded', function() {
    // Auto-focus on the first input field
    const firstInput = document.querySelector('input');
    if (firstInput) {
        firstInput.focus();
    }
    
    // Form validation
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            const passwordInput = form.querySelector('input[type="password"]');
            if (passwordInput && passwordInput.value.length < 6) {
                e.preventDefault();
                alert('Password must be at least 6 characters long');
            }
        });
    });
    
    // Show modal immediately when page loads
    const modal = document.querySelector('.modal');
    if (modal) {
        modal.style.display = 'flex';
    }
    
    // Close modal when clicking outside
    modal.addEventListener('click', function(e) {
        if (e.target === modal) {
            // Prevent closing for this implementation as it's a login requirement
            // modal.style.display = 'none';
        }
    });
});