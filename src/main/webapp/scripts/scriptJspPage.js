document.addEventListener("DOMContentLoaded", function() {

    // Gestion du bouton de fermeture
    const urlParams = new URLSearchParams(window.location.search);
    const locale = urlParams.get('locale') || 'en_US';
    const enButton = document.querySelector('button[value="en_US"]');
    const frButton = document.querySelector('button[value="fr_FR"]');

    // Masquer le bouton de la langue courante
    if (locale === 'en_US') {
        enButton.style.display = 'none';
    } else if (locale === 'fr_FR') {
        frButton.style.display = 'none';
    }
});