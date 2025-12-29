<template>
    <header>
        <span>
            <h1 class="head-text">
                <span>Гребенюк Леонид Сергеевич, </span>
                <br class="head-br"/>
                <span>p3232, </span>
                <br class="head-br"/>
                <span>вариант 342341</span>
            </h1>
        </span>
    </header>
</template>

<style>
.head-br {
    @media (min-width: 1267px) {
        display: none;
    }
}

header {
    font-family: fantasy;
}

.head-text {
    font-size: 20px;
    @media (max-width: 737px) {
        display: none;
    }
}
</style>
