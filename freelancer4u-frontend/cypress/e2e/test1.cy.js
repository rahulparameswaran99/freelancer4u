describe('empty spec', () => {
  before(() => {
    cy.visit('http://localhost:8080')

   // delete all jobs
    cy.request('DELETE', 'http://localhost:8080/api/job')

    // wait for 1 second
    cy.wait(1000)
  })

  it('Jobs Seite aufrufen und überprüfen', () => {
    cy.get('a[href="#/jobs"]').click()
    cy.location('hash').should('include', 'jobs')
  }) 

  it('1. Job erstellen', () => {
    cy.get('#description').type('Job Number 1')
    cy.get('select').select('TEST').should('have.value', 'TEST')
    cy.get('#earnings').type('100')
    cy.get('button.btn-primary').click()
  })
  it('2. Job erstellen', () => {
    cy.get('#description').clear()
    cy.get('#earnings').clear()
    cy.get('#description').type('Job Number 2')
    cy.get('select').select('IMPLEMENT').should('have.value', 'IMPLEMENT')
    cy.get('#earnings').type('200')
    cy.get('button.btn-primary').click()
  })
  it('3. Job erstellen', () => {
    cy.get('#description').clear()
    cy.get('#earnings').clear()
    cy.get('#description').type('Job Number 3')
    cy.get('select').select('REVIEW').should('have.value', 'REVIEW')
    cy.get('#earnings').type('300')
    cy.get('button.btn-primary').click()
  })
  it('Table Elemente überprüfen', () => {
    cy.get('tbody>tr').should('have.length', 3)
  })
  it('4. Job erstellen', () => {
    cy.get('#description').clear()
    cy.get('#earnings').clear()
    cy.get('#description').type('Job Number 4')
    cy.get('select').select('TEST').should('have.value', 'TEST')
    cy.get('#earnings').type('300')
    cy.get('button.btn-primary').click()
  })
  it('5. Job erstellen', () => {
    cy.get('#description').clear()
    cy.get('#earnings').clear()
    cy.get('#description').type('Job Number 5')
    cy.get('select').select('IMPLEMENT').should('have.value', 'IMPLEMENT')
    cy.get('#earnings').type('300')
    cy.get('button.btn-primary').click()
  })
  it('2. Page aufrufen und Job 5 überprüfen', () => {
    cy.get('a[href="#/jobs?page=2"]').click()
    cy.location('hash').should('include', 'jobs')
    cy.get('tbody>tr').should('have.length', 1)
  })

})